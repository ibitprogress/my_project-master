package ua.autostock.specification;

import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;



public class CarSpecification implements Specification {

    private String make;
    private String model;
    private String vin;

    List<Predicate> predicates = new ArrayList<Predicate>();

    @Override
    public Predicate toPredicate(Root root, CriteriaQuery q, CriteriaBuilder cb) {
        if(make != null && model != null){
            Predicate byMakeAndModel = cb.and(cb.like(root.get("make"), "%"+make+"%"),cb.like(root.get("model"), "%"+model+"%"));
            predicates.add(byMakeAndModel);
        }else{
            if(make != null){
                Predicate byMake = cb.like(root.get("make"), "%"+make+"%");
                predicates.add(byMake);
            }
            if(model != null){
                Predicate byModel = cb.like(root.get("model"), "%"+model+"%");
                predicates.add(byModel);
            }
        }
        if(vin != null){
            Predicate byVin = cb.equal(root.get("vin"), vin);
            predicates.add(byVin);
        }
        return cb.and(predicates.toArray(new Predicate[predicates.size()]));
    }


    public CarSpecification(String make, String model, String vin) {
        this.make = make;
        this.model = model;
        this.vin = vin;
    }
}
