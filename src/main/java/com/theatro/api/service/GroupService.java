package com.theatro.api.service;

import com.theatro.api.dao.GroupDao;
import com.theatro.api.response.Group;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class GroupService {

    @Autowired
    GroupDao groupDao;

    public List<Group> listGroups(String storeName){
        return groupDao.getGroupList(storeName);
    }
    public Group getGroup(String store,String group){
        return groupDao.getGroup(store,group);
    }
}
