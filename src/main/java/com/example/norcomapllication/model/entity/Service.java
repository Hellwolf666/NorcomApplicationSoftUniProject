package com.example.norcomapllication.model.entity;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "services")
public class Service extends BaseEntity{
    public String name;
    public List<MobilePlan> mobilePlans;



    @Column(unique = true,nullable = false)
    public String getName() {
        return name;
    }

    public Service setName(String name) {
        this.name = name;
        return this;
    }
    @ManyToMany
    @JoinTable(name = "services_mobile_plans",
            joinColumns = @JoinColumn(name = "service_id", referencedColumnName = "id"))
    public List<MobilePlan> getMobilePlans() {
        return mobilePlans;
    }

    public Service setMobilePlans(List<MobilePlan> mobilePlans) {
        this.mobilePlans = mobilePlans;
        return this;
    }
}
