package com.football.mappers;

public interface IMapper<I, O> {
    public O map(I in);
}
