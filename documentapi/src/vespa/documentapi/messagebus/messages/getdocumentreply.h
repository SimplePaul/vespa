// Copyright 2017 Yahoo Holdings. Licensed under the terms of the Apache 2.0 license. See LICENSE in the project root.
#pragma once

#include "documentacceptedreply.h"

namespace document { class Document; }

namespace documentapi {

class GetDocumentReply : public DocumentAcceptedReply {
private:
    using DocumentSP = std::shared_ptr<document::Document>;
    DocumentSP _document;
    uint64_t   _lastModified;

public:
    /**
     * Convenience typedef.
     */
    typedef std::unique_ptr<GetDocumentReply> UP;
    typedef std::shared_ptr<GetDocumentReply> SP;

    /**
     * Constructs a new reply for deserialization.
     */
    GetDocumentReply();
    ~GetDocumentReply();

    /**
     * Constructs a new document get reply.
     *
     * @param document The document requested.
     */
    GetDocumentReply(DocumentSP document);

    /**
     * Returns the document retrieved.
     *
     * @return The document.
     */
    const DocumentSP & getDocument() const { return _document; }

    /**
     * Sets the document retrieved.
     *
     * @param document The document.
     */
    void setDocument(DocumentSP document);

    /**
     * Returns the date the document was last modified.
     *
     * @return The date.
     */
    uint64_t getLastModified() const { return _lastModified; };

    /**
     * Set the date the document was last modified.
     *
     * @param lastModified The date.
     */
    void setLastModified(uint64_t lastModified) { _lastModified = lastModified; }

    string toString() const override { return "getdocumentreply"; }
};

}

