package com.socialmedia.repository;


import com.socialmedia.entity.User;
import com.socialmedia.views.VwSearchUser;
import com.socialmedia.views.VwUserAvatar;
import com.socialmedia.views.VwUserProfile;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {

    Optional<User> findOptionalByUserNameAndPassword(String userName, String password);

    List<User> findAllByUserNameContaining(String userName);

    @Query("select new com.socialmedia.views.VwUserProfile(u.name,u.userName,u.avatar,u.followerCount,u.followingCount,u.about,u.bornDate,u.phone,u.address) from User u where u.id=?1")
    VwUserProfile getByAuthId(Long id);

    @Query("select new com.socialmedia.views.VwUserAvatar(u.id,u.userName,u.avatar) from User u where u.id=?1")
    VwUserAvatar getUserAvatar(Long id);

    @Query("select new com.socialmedia.views.VwUserAvatar(u.id,u.userName,u.avatar) from User u ")
    List<VwUserAvatar> getUserAvatarList();

    @Query("select new com.socialmedia.views.VwSearchUser(u.id,u.userName,u.name,u.avatar) from User u where u.userName ilike ?1")
    List<VwSearchUser> getAllByUserName(String userName);

    List<User> findAllByUserNameContainingAndIdNotIn(String s, List<Long> followIds, Pageable pageable);

    List<User> findAllByUserNameLikeAndIdNotIn(String s, List<Long> followIds, PageRequest of);

    List<User> findAllByIdIn(List<Long> allFollowing, PageRequest of);
}
