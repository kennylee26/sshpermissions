package com.tgyt.permissions.dao;

import org.springframework.stereotype.Repository;

import com.tgyt.framework.dao.hspring.BaseDAO;
import com.tgyt.permissions.model.RoleAuth;

@Repository(value="roleAuthDao")
public class RoleAuthDao extends BaseDAO<RoleAuth> {
}
