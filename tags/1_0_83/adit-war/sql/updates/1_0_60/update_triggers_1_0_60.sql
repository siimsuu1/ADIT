create or replace 
trigger &&ADIT_SCHEMA..TR_DOCUMENT_SHARING_LOG
  after insert or update or delete
  on &&ADIT_SCHEMA..DOCUMENT_SHARING referencing old as old new as new
  for each row
DECLARE
  operation varchar2(100);
  DOCUMENT_SHARING_new &&ADIT_SCHEMA..DOCUMENT_SHARING%ROWTYPE;
  DOCUMENT_SHARING_old &&ADIT_SCHEMA..DOCUMENT_SHARING%ROWTYPE;
BEGIN
 
  if inserting then
    operation := 'INSERT';
  else
    if updating then
      operation := 'UPDATE';
    else
      operation := 'DELETE';
    end if;
  end if; 

  DOCUMENT_SHARING_new.ID := :new.ID;
  DOCUMENT_SHARING_new.DOCUMENT_ID := :new.DOCUMENT_ID;
  DOCUMENT_SHARING_new.USER_CODE := :new.USER_CODE;
  DOCUMENT_SHARING_new.USER_NAME := :new.USER_NAME;
  DOCUMENT_SHARING_new.SHARING_TYPE := :new.SHARING_TYPE;
  DOCUMENT_SHARING_new.TASK_DESCRIPTION := :new.TASK_DESCRIPTION;
  DOCUMENT_SHARING_new.CREATION_DATE := :new.CREATION_DATE;
  DOCUMENT_SHARING_new.DVK_STATUS_ID := :new.DVK_STATUS_ID;
  DOCUMENT_SHARING_new.WF_STATUS_ID := :new.WF_STATUS_ID;
  DOCUMENT_SHARING_new.FIRST_ACCESS_DATE := :new.FIRST_ACCESS_DATE;
  DOCUMENT_SHARING_new.DVK_ID := :new.DVK_ID;
  
  DOCUMENT_SHARING_old.ID := :old.ID;
  DOCUMENT_SHARING_old.DOCUMENT_ID := :old.DOCUMENT_ID;
  DOCUMENT_SHARING_old.USER_CODE := :old.USER_CODE;
  DOCUMENT_SHARING_old.USER_NAME := :old.USER_NAME;
  DOCUMENT_SHARING_old.SHARING_TYPE := :old.SHARING_TYPE;
  DOCUMENT_SHARING_old.TASK_DESCRIPTION := :old.TASK_DESCRIPTION;
  DOCUMENT_SHARING_old.CREATION_DATE := :old.CREATION_DATE;
  DOCUMENT_SHARING_old.DVK_STATUS_ID := :old.DVK_STATUS_ID;
  DOCUMENT_SHARING_old.WF_STATUS_ID := :old.WF_STATUS_ID;
  DOCUMENT_SHARING_old.FIRST_ACCESS_DATE := :old.FIRST_ACCESS_DATE;
  DOCUMENT_SHARING_old.DVK_ID := :old.DVK_ID;

  &&ADIT_SCHEMA..ADITLOG.LOG_DOCUMENT_SHARING(
    DOCUMENT_SHARING_new,
    DOCUMENT_SHARING_old,
    operation
  );

END;
/