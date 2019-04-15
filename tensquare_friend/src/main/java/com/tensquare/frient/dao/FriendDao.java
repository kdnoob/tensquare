package com.tensquare.frient.dao;

import com.tensquare.frient.pojo.Friend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface FriendDao extends JpaRepository<Friend, String> {

    /**
     * 根据用户ID与被关注用户ID查询记录个数
     *
     * @param userid
     * @param friendid
     * @return
     */
    public Friend findFriendByUseridAndFriendid(String userid, String friendid);
    /**
     * 更新为互相喜欢
     *
     * @param userid
     * @param friendid
     */
    @Modifying
    @Query(value = "UPDATE tb_friend SET islike=? WHERE userid=? AND friendid=?", nativeQuery = true)
    public void updateLike(String islike, String userid, String friendid);


    /**
     * 删除喜欢
     * @param userid
     * @param friendid
     */
    @Modifying
    public void deleteFriendByUseridAndFriendid(String userid,String friendid);

}
