package ar.edu.ucc.arqsoft.casos.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public abstract class DaoGenericoImp<E, ID extends Serializable> implements DaoGenerico<E, ID> {

	@Autowired
	private SessionFactory sessionFactory;

	protected Class<? extends E> daoType;

	@SuppressWarnings("unchecked")
	public DaoGenericoImp() {
		Type t = getClass().getGenericSuperclass();
		ParameterizedType pt = (ParameterizedType) t;
		daoType = (Class<? extends E>) pt.getActualTypeArguments()[0];
	}

	protected Session currentSession() {
		Session session;
		try {
			session = sessionFactory.getCurrentSession();
		} catch (HibernateException e) {
			session = sessionFactory.openSession();
		}
		return session;
	}

	public void add(E entity) {
		currentSession().save(entity);
	}

	public void saveOrUpdate(E entity) {
		currentSession().saveOrUpdate(entity);
	}

	public void update(E entity) {
		currentSession().update(entity);
	}

	public void merge(E entity) {
		currentSession().merge(entity);
	}

	public void remove(E entity) {
		currentSession().delete(entity);
	}

	public E load(ID key) {
		return (E) currentSession().get(daoType, key);
	}

	@SuppressWarnings("unchecked")
	public List<E> getAll() {

		CriteriaQuery<E> cq = (CriteriaQuery<E>) currentSession().getCriteriaBuilder().createQuery(daoType);
		cq.from(daoType);

		return currentSession().createQuery(cq).getResultList();
	}

	protected List<E> getByProperties(Map<String, Object> properties) {
		CriteriaQuery<E> queryByProperties = createQueryByProperties(properties);

		TypedQuery<E> query = currentSession().createQuery(queryByProperties);
		
		
		Set<String> keySet = properties.keySet();
		for (String key : keySet) {
			Object value = properties.get(key);
			query.setParameter(key, value);
		}
		
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	private CriteriaQuery<E> createQueryByProperties(Map<String, Object> properties) {
		CriteriaBuilder cb = currentSession().getCriteriaBuilder();
		CriteriaQuery<E> cq = (CriteriaQuery<E>) cb.createQuery(daoType);

		Root<E> root = (Root<E>) cq.from(daoType);

		Set<Entry<String, Object>> entrySet = properties.entrySet();
		List<Predicate> predicateList = new ArrayList<Predicate>();
		for (Entry<String, Object> entry : entrySet) {
			String key = entry.getKey();
			Object value = entry.getValue();
			ParameterExpression<?> val = cb.parameter(value.getClass(), key);
			Predicate equal = cb.equal(root.get(key), val);

			predicateList.add(equal);
		}
		cq = cq.where(cb.and(predicateList.toArray(new Predicate[predicateList.size()])));
		return cq;
	}
	
	
	protected List<E> executeQuery(String hql) {
		
		Query query = currentSession().createQuery(hql);
		
		@SuppressWarnings("unchecked")
		List<E> list = query.getResultList();
		return list;
	}
	
	protected List<E> executeQuery(String hql, Map<String, Object> hqlParameters) {
		
		Query query = currentSession().createQuery(hql);
		
		for (String key : hqlParameters.keySet()) {
		  query.setParameter(key, hqlParameters.get(key));
		}
		
		@SuppressWarnings("unchecked")
		List<E> list = query.getResultList();
		 
		return list;
	}
}
