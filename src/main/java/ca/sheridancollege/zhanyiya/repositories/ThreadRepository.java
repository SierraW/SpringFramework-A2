package ca.sheridancollege.zhanyiya.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import ca.sheridancollege.zhanyiya.beans.Thread;

public interface ThreadRepository extends JpaRepository<Thread, Long> {

}