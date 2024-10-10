package com.socialmedia.repository;

import com.socialmedia.entity.Like;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository  extends JpaRepository<Like,Long> {
}
