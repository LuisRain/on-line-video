package com.video.external.note.repository;

import com.video.external.note.bean.domain.NoteLog;
import org.springframework.data.repository.CrudRepository;

/**
 * 连接短信日志表
 *
 * @author: master
 * @date: 2018/8/11
 */
public interface NoteLongRepository extends CrudRepository<NoteLog, Long> {

}
