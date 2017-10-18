// Copyright 2017 Yahoo Holdings. Licensed under the terms of the Apache 2.0 license. See LICENSE in the project root.
package com.yahoo.vespa.hosted.controller.athenz;

/**
 * @author bjorncs
 */
public interface AthenzClientFactory {

    ZmsClient createZmsClientWithServicePrincipal();

    ZtsClient createZtsClientWithServicePrincipal();

    ZmsClient createZmsClientWithAuthorizedServiceToken(NToken authorizedServiceToken);

}