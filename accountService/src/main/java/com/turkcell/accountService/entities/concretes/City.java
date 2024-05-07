package com.turkcell.accountService.entities.concretes;


import com.turkcell.accountService.core.entities.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "cities")
public class City extends BaseEntity<Integer> {

    @Column(name = "city_name", nullable = false)
    private String cityName;
}
