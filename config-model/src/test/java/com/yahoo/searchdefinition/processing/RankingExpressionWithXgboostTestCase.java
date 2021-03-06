// Copyright 2018 Yahoo Holdings. Licensed under the terms of the Apache 2.0 license. See LICENSE in the project root.
package com.yahoo.searchdefinition.processing;

import com.yahoo.path.Path;
import com.yahoo.searchdefinition.parser.ParseException;
import org.junit.Test;

/**
 * @author grace-lam
 */
public class RankingExpressionWithXgboostTestCase {

    private final Path applicationDir = Path.fromString("src/test/integration/xgboost/");
    private final static String vespaExpression = "if (f29 < -0.1234567, if (f56 < -0.242398, 1.71218, -1.70044), if (f109 < 0.8723473, -1.94071, 1.85965)) + " +
            "if (f60 < -0.482947, if (f29 < -4.2387498, 0.784718, -0.96853), -6.23624)";

    @Test
    public void testXgboostReference() {
        RankProfileSearchFixture search = fixtureWith("xgboost('xgboost.2.2.json')");
        search.assertFirstPhaseExpression(vespaExpression, "my_profile");
    }

    @Test
    public void testNestedXgboostReference() {
        RankProfileSearchFixture search = fixtureWith("5 + sum(xgboost('xgboost.2.2.json'))");
        search.assertFirstPhaseExpression("5 + reduce(" + vespaExpression + ", sum)", "my_profile");
    }

    private RankProfileSearchFixture fixtureWith(String firstPhaseExpression) {
        return fixtureWith(firstPhaseExpression, null, null,
                new RankingExpressionWithTensorFlowTestCase.StoringApplicationPackage(applicationDir));
    }

    private RankProfileSearchFixture fixtureWith(String firstPhaseExpression,
                                                 String constant,
                                                 String field,
                                                 RankingExpressionWithTensorFlowTestCase.StoringApplicationPackage application) {
        try {
            return new RankProfileSearchFixture(
                    application,
                    application.getQueryProfiles(),
                    "  rank-profile my_profile {\n" +
                            "    first-phase {\n" +
                            "      expression: " + firstPhaseExpression +
                            "    }\n" +
                            "  }",
                    constant,
                    field);
        } catch (ParseException e) {
            throw new IllegalArgumentException(e);
        }
    }

}

