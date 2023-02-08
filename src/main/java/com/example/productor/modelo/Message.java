package com.example.productor.modelo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
public class Message {
    private int id;
    private String nombre;
    private String apellido;
    private String pedido;
}
