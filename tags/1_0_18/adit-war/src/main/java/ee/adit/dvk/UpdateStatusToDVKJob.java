package ee.adit.dvk;

import org.apache.log4j.Logger;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import ee.adit.service.DocumentService;

/**
 * Updates document statuses to DVK client.
 * 
 * @author Marko Kurm, Microlink Eesti AS, marko.kurm@microlink.ee
 */
public class UpdateStatusToDVKJob extends QuartzJobBean {

    private static Logger logger = Logger.getLogger(UpdateStatusToDVKJob.class);

    private DocumentService documentService;

    @Override
    protected void executeInternal(JobExecutionContext arg0) throws JobExecutionException {

        try {
            logger.info("Executing scheduled job: Updating document statuses to DVK");

            // Update document statuses from DVK
            int updatedDocumentsCount = this.getDocumentService().updateDocumentsToDVK();

            logger.debug("Document statuses updated to DVK (" + updatedDocumentsCount + ")");

        } catch (Exception e) {
            logger.error("Error executing scheduled DVK statuses update: ", e);
        }

    }

    public DocumentService getDocumentService() {
        return documentService;
    }

    public void setDocumentService(DocumentService documentService) {
        this.documentService = documentService;
    }

}
