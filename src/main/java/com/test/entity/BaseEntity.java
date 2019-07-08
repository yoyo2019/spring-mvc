package com.test.entity;


import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 * 基础父类
 * </p>
 *
 * @author hubin
 * @since 2018-08-11
 */
@Data
@Accessors(chain = true)
public class BaseEntity {
  private Long id;

}
