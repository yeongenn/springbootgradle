package pack.model;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import pack.controller.BoardBean;

@Mapper
public interface DataMapInterface {

    // 어노테이션 버전으로 바꿔보기!

    // 추상 메서드명은 mapper.xml에 id명과 일치시켜야 한다
    List<Board> selectList();

    List<Board> selectSearch(BoardBean bean);

    Board selectOne(String num);

    int insert(BoardBean bean);

    int update(BoardBean bean);

    void updateReadcnt(String num);

    int delete(String num);


}
