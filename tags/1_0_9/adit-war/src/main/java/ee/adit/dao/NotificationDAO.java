package ee.adit.dao;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import ee.adit.dao.pojo.Notification;
import ee.adit.exception.AditInternalException;

public class NotificationDAO extends HibernateDaoSupport {
    private static Logger logger = Logger.getLogger(NotificationDAO.class);

    @SuppressWarnings("unchecked")
    public List<Notification> getUnsentNotifications() {
        List<Notification> result = null;

        try {
            logger.debug("Finding unsent notifications... ");
            result = this.getHibernateTemplate().find(
                    "from Notification notification where notification.notificationId is null");
        } catch (Exception e) {
            logger.error("Exception while finding notifications: ", e);
        }

        return result;
    }

    public Long save(final Notification notification) {
        logger.debug("Attemptyng to save notification...");
        Long result = null;

        result = (Long) this.getHibernateTemplate().execute(new HibernateCallback() {

            @Override
            public Object doInHibernate(Session session) throws HibernateException, SQLException {
                session.saveOrUpdate(notification);
                logger.debug("Successfully saved notification with ID: " + notification.getId());
                return notification.getId();
            }
        });

        return result;
    }

    public Long getUnsentNotifications(Date comparisonDate) {
        Long result = 0L;
        String sql = "select count(*) from Notification where notificationId is null and eventDate <= :comparisonDate";

        Session session = null;
        try {
            session = this.getSessionFactory().openSession();
            Query query = session.createQuery(sql);
            query.setParameter("comparisonDate", comparisonDate);
            result = (Long) query.uniqueResult();
        } catch (Exception e) {
            throw new AditInternalException("Error while fetching unsent notifications: ", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }

        return result;
    }
}
