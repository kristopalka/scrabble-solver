package com.scrabble.backend.resolving.algorithm.solver.wordsfinder.correctselector;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString
@EqualsAndHashCode
public
class Block {
    public int start;
    public int end;
    public String content;

    public Block(int start, String content) {
        this.start = start;
        this.end = start + content.length() - 1;
        this.content = content;
    }

    public int length() {
        return end - start + 1;
    }
}
