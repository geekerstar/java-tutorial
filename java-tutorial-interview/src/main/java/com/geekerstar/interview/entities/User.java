package com.geekerstar.interview.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @auther zzyy
 * @create 2018-08-10 14:53
 */
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Data
public class User extends BaseEntity
{
    private Integer id;
    private String  userName;
    private Integer age;
}
