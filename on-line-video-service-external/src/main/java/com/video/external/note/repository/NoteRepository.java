package com.video.external.note.repository;

import com.video.external.note.bean.domain.Note;
import org.springframework.data.repository.CrudRepository;

/**
 * 连接短信表
 *
 * @author: master
 * @date: 2018/8/11
 */
public interface NoteRepository extends CrudRepository<Note, Long> {

}
