package com.tensquare.frient.service;

import com.tensquare.frient.dao.FriendDao;
import com.tensquare.frient.dao.NoFriendDao;
import com.tensquare.frient.pojo.Friend;
import com.tensquare.frient.pojo.NoFriend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class FriendService {

    @Autowired
    private FriendDao friendDao;

    @Autowired
    private NoFriendDao noFriendDao;

    public int addFriend(String userid, String friendid) {
        //判断如果用户已经添加了这个好友，则不进行任何操作,返回0
        if (friendDao.findFriendByUseridAndFriendid(userid, friendid) !=null) {
            return 0;
        }
        //向喜欢表中添加记录
        Friend friend = new Friend();
        friend.setUserid(userid);
        friend.setFriendid(friendid);
        friend.setIslike("0");
        friendDao.save(friend);

        //判断对方是否喜欢你，如果喜欢，将islike设置为1
        if (friendDao.findFriendByUseridAndFriendid(friendid, userid) !=null) {
            friendDao.updateLike("1",userid, friendid);
            friendDao.updateLike("1",friendid, userid);
        }
        return 1;
    }

    public void addNoFriend(String userid, String friendid) {
        NoFriend noFriend=new NoFriend();
        noFriend.setUserid(userid);
        noFriend.setFriendid(friendid);
        noFriendDao.save(noFriend);
    }
}
