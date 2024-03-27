package com.danoc.danoc.dto.object;

import java.sql.Timestamp;

import java.util.List;
import java.util.ArrayList;

import com.danoc.danoc.repository.resultSet.CommentListResultSet;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CommentListItem {
    private String name;
    private String ctnt;
    private Timestamp date;

    public CommentListItem(CommentListResultSet resultSet) {
        this.name = resultSet.getName();
        this.ctnt = resultSet.getCtnt();
        this.date = resultSet.getDate();
    }

    public static List<CommentListItem> copyList(List<CommentListResultSet> resultSets) {
        List<CommentListItem> list = new ArrayList<>();
        for (CommentListResultSet resultSet : resultSets) {
            CommentListItem commentListItem = new CommentListItem(resultSet);
            list.add(commentListItem);
        }
        return list;
    }
}
