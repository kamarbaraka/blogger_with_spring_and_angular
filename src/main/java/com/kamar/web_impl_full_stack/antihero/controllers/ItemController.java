package com.kamar.web_impl_full_stack.antihero.controllers;


import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = {"item"})
public class ItemController {

    private final Map<String , String > items = new HashMap<>();

    @PostMapping
    public Map<String , String > postItem(@RequestBody Map<String , String > item){

        this.items.putAll(item);

        return item;
    }

    @GetMapping(value = {"/{id}"})
    public String getItem(@PathVariable("id") String id){

        return this.items.get(id);
    }

    @GetMapping
    public Map<String , String > getAllItems(){

        return this.items;
    }

    @DeleteMapping(value = {"/{id}"})
    public Map<String , String > deleteItem(@PathVariable("id") String id){

        this.items.remove(id);
        return this.items;
    }
}
