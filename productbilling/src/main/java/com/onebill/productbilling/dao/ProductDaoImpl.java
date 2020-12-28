package com.onebill.productbilling.dao;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Repository;

import com.onebill.productbilling.Entities.Plan;
import com.onebill.productbilling.Entities.PlanCharge;
import com.onebill.productbilling.Entities.PlanDetail;
import com.onebill.productbilling.Entities.PlanOverdue;
import com.onebill.productbilling.Entities.Product;
import com.onebill.productbilling.dto.PlanChargeRespDto;
import com.onebill.productbilling.dto.PlanDetailRespDto;
import com.onebill.productbilling.dto.PlanOverdueRespDto;
import com.onebill.productbilling.dto.PlanRespDto;
import com.onebill.productbilling.dto.ProductDto;

@Repository
public class ProductDaoImpl implements ProductDao {

	@PersistenceContext
	EntityManager manager;

	@Override
	@Transactional
	public ProductDto addProduct(ProductDto pro) {
		Product product = new Product();
		ProductDto dto = new ProductDto();
		BeanUtils.copyProperties(pro, product);
		manager.persist(product);
		BeanUtils.copyProperties(product, dto);
		return dto;
	}

	@Override
	public ProductDto getProduct(String productName) {
		TypedQuery<Product> query = manager.createQuery("from Product U where U.productName= :value", Product.class);
		query.setParameter("value", productName);
		Product pro = query.getSingleResult();
		ProductDto dto = new ProductDto();
		BeanUtils.copyProperties(pro, dto);
		ModelMapper mapper = new ModelMapper();
		Type listType = new TypeToken<List<PlanRespDto>>() {
		}.getType();
		List<PlanRespDto> dlist = mapper.map(pro.getPlan(), listType);
		dto.setPlan(dlist);
		return dto;
	}

	@Override
	@Transactional
	public ProductDto getProductWithId(int productId) {
		Product pro = manager.find(Product.class, productId);
		if (pro != null) {
			ProductDto dto = new ProductDto();
			BeanUtils.copyProperties(pro, dto);
			ModelMapper mapper = new ModelMapper();
			Type listType = new TypeToken<List<PlanRespDto>>() {
			}.getType();
			List<PlanRespDto> dlist = mapper.map(pro.getPlan(), listType);
			dto.setPlan(dlist);
			return dto;
		} else {
			return null;
		}
	}

	@Override
	public List<ProductDto> getAllProducts() {
		List<Product> list = manager.createQuery("FROM Product", Product.class).getResultList();
		ModelMapper mapper = new ModelMapper();
		Type listType = new TypeToken<List<ProductDto>>() {
		}.getType();
		List<ProductDto> dto = mapper.map(list, listType);
		List<List<Plan>> l = new ArrayList<>();
		for (Product p : list) {
			l.add(p.getPlan());
		}
		int count = 0;
		ModelMapper mapp = new ModelMapper();
		Type typelist = new TypeToken<List<PlanRespDto>>() {
		}.getType();
		for (List<Plan> grp : l) {

			List<PlanRespDto> dlist = mapp.map(grp, typelist);
			dto.get(count++).setPlan(dlist);
		}
		return dto;

	}

	@Override
	@Transactional
	public ProductDto updateProduct(ProductDto pro) {
		pro.setPlan(new ArrayList<PlanRespDto>());
		Product prod = manager.find(Product.class, pro.getProductId());
		List<Plan> planlist=prod.getPlan();
		pro.getPlan().clear();
		Product product = new Product();
		BeanUtils.copyProperties(pro, product);
		product.getPlan().addAll(planlist);
		manager.merge(product);
		ProductDto dto = new ProductDto();
		BeanUtils.copyProperties(product, dto);
		return dto;
	}

	@Override
	@Transactional
	public ProductDto removeproduct(int productId) {
		Product product = manager.find(Product.class, productId);
		if (product != null) {
			manager.remove(product);
			ProductDto dto = new ProductDto();
			BeanUtils.copyProperties(product, dto);
			return dto;
		} else {
			return null;
		}
	}

	@Override
	public List<PlanRespDto> getProductPlan(int productId) {
		Product pro = new Product();
		pro.setProductId(productId);
		TypedQuery<Plan> query = manager.createQuery("from Plan P where P.product=:value", Plan.class);
		query.setParameter("value", pro);
		List<Plan> list = query.getResultList();
		ModelMapper mapper = new ModelMapper();
		Type listType = new TypeToken<List<PlanRespDto>>() {
		}.getType();
		List<PlanRespDto> dto = mapper.map(list, listType);
		return dto;
	}

	@Override
	public List<PlanDetailRespDto> getPlanDetail(int productId, int planId) {
		Plan p = new Plan();
		p.setPlanId(planId);
		TypedQuery<PlanDetail> query = manager.createQuery("from PlanDetail P where P.plan=:value", PlanDetail.class);
		query.setParameter("value", p);
		List<PlanDetail> list = query.getResultList();
		ModelMapper mapper = new ModelMapper();
		Type listType = new TypeToken<List<PlanDetailRespDto>>() {
		}.getType();
		return mapper.map(list, listType);
	}

	@Override
	public List<PlanChargeRespDto> getPlanCharge(int productId, int planId) {
		Plan p = new Plan();
		p.setPlanId(planId);
		TypedQuery<PlanCharge> query = manager.createQuery("from PlanCharge P where P.plan=:value", PlanCharge.class);
		query.setParameter("value", p);
		List<PlanCharge> list = query.getResultList();
		ModelMapper mapper = new ModelMapper();
		Type listType = new TypeToken<List<PlanChargeRespDto>>() {
		}.getType();
		List<PlanChargeRespDto> dto = mapper.map(list, listType);
		return dto;
	}

	@Override
	public List<PlanOverdueRespDto> getPlanOverdue(int productId, int planId) {
		Plan p = new Plan();
		p.setPlanId(planId);
		TypedQuery<PlanOverdue> query = manager.createQuery("from PlanOverdue P where P.plan=:value",
				PlanOverdue.class);
		query.setParameter("value", p);
		List<PlanOverdue> list = query.getResultList();
		ModelMapper mapper = new ModelMapper();
		Type listType = new TypeToken<List<PlanOverdueRespDto>>() {
		}.getType();
		List<PlanOverdueRespDto> dto = mapper.map(list, listType);
		return dto;
	}

}
