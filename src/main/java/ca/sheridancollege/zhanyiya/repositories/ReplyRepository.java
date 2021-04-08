package ca.sheridancollege.zhanyiya.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ca.sheridancollege.zhanyiya.beans.Reply;

public interface ReplyRepository extends JpaRepository<Reply, Long> {
	public List<Reply> findByThread_IdOrderByTimeAsc(Long id);
}
