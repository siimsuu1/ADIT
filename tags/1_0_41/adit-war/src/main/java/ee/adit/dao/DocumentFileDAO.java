package ee.adit.dao;

import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import ee.adit.dao.pojo.DocumentFileDeflateResult;

/**
 * Document file data access class. Provides methods for retrieving and manipulating
 * document file data.
 * 
 * @author Marko Kurm, Microlink Eesti AS, marko.kurm@microlink.ee
 * @author Jaak Lember, Interinx, jaak@interinx.com
 */
public class DocumentFileDAO extends HibernateDaoSupport {
    
    private static Logger logger = Logger.getLogger(DocumentFileDAO.class);

    /**
     * Deflate document file.
     * 
     * @param documentId document ID
     * @param fileId document file ID
     * @param markDeleted mark document deleted
     * @param failIfSignature
     *     Should the method return an error message if someone attempts to
     *     deflate a signature container.
     * @return deflation result code
     */
    public String deflateDocumentFile(
    		final long documentId,
    		final long fileId,
    		final boolean markDeleted,
    		final boolean failIfSignature) {
    	
        logger.debug("deflateDocumentFile starting...");
        DocumentFileDeflateResult result = (DocumentFileDeflateResult) getHibernateTemplate().execute(
                new HibernateCallback() {
                    public Object doInHibernate(Session session) throws HibernateException, SQLException {
                        Query q = session.getNamedQuery("DEFLATE_FILE");
                        q.setLong("documentId", documentId);
                        q.setLong("fileId", fileId);
                        q.setBoolean("markDeleted", markDeleted);
                        q.setBoolean("failIfSignature", failIfSignature);

                        logger.debug("Executing stored procedure DEFLATE_FILE");
                        return q.uniqueResult();
                    }
                });

        logger.debug("File deflation result code is: " + result.getResultCode());

        return result.getResultCode();
    }

    /**
     * Replaces file contents in database with MD5 hash. Also saves offset
     * markers of file contents in signature container.
     * 
     * @param documentId
     *     ID of affected document
     * @param fileId
     *     ID of affected file
     * @param dataStartOffset
     *     Start offset of data file contents in signature container
     * @param dataEndOffset
     *     End offset of data file contents in signature container
     * @return
     *     Result code
     */
    public String removeSignedFileContents(
    	final long documentId,
    	final long fileId,
    	final long dataStartOffset,
    	final long dataEndOffset) {
        
    	logger.debug("removeSignedFileContents starting...");
        DocumentFileDeflateResult result = (DocumentFileDeflateResult) getHibernateTemplate().execute(
            new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException, SQLException {
                    Query q = session.getNamedQuery("REMOVE_SIGNED_FILE_CONTENTS");
                    q.setLong("documentId", documentId);
                    q.setLong("fileId", fileId);
                    q.setLong("ddocStartOffset", dataStartOffset);
                    q.setLong("ddocEndOffset", dataEndOffset);

                    logger.debug("Executing stored procedure REMOVE_SIGNED_FILE_CONTENTS");
                    return q.uniqueResult();
                }
            });

        logger.debug("REMOVE_SIGNED_FILE_CONTENTS result code is: " + result.getResultCode());

        return result.getResultCode();
    }
}
