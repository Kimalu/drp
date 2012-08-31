package com.Kimalu.drp.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import org.springframework.stereotype.Component;

@Entity
@Component
@DiscriminatorValue("temiClientType")
public class TemiClientType extends AbstractDataDict {

}
