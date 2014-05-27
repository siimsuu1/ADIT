ALTER TABLE &&ADIT_SCHEMA..DOCUMENT ADD (content  VARCHAR2(4000));
COMMENT ON COLUMN &&ADIT_SCHEMA..DOCUMENT.content IS 'Textual content of the document. Used to keep email body.';

INSERT INTO &&ADIT_SCHEMA..DOCUMENT_SHARING_TYPE(SHORT_NAME, DESCRIPTION) VALUES('send_email', 'Documendi saatmine meiliga');

ALTER TABLE &&ADIT_SCHEMA..DOCUMENT_SHARING MODIFY (user_code null);

ALTER TABLE &&ADIT_SCHEMA..DOCUMENT_SHARING ADD (user_email  VARCHAR2(255));
COMMENT ON COLUMN &&ADIT_SCHEMA..DOCUMENT_SHARING.user_email IS 'Recipient email if document is sent by email';