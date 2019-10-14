package sop.service.transactions.model;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Table(name = "log")
public class Log {
    //    @Id @GeneratedValue val id: Long ? = null,
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

//    var title: String? = null,
//    @OneToMany(mappedBy = "todo", fetch = FetchType.EAGER, cascade = arrayOf(CascadeType.REMOVE), orphanRemoval = true)
//    var todoItems: List<TodoItem>? = emptyList()) {
//    override fun toString(): String {
//        return "Todo(id=$id, title=$title, todoItems=$todoItems)"
}
