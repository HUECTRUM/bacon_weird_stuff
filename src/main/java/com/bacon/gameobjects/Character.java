package com.bacon.gameobjects;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Character {
    public List<Card> stylesKit;
    public List<Card> basesKit;
}
