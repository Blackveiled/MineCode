/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.blackveiled.minecode.compiler;

/**
 *
 * @author Blackveiled
 */
public abstract class MineCodeEncoder {

    public MineCodeEncoder() {

    }

    public static byte encodeCharacter(char c) {
        switch (c) {
            case '\u0000':
                return 0x00;
            case '\u0001':
                return 0x01;
            case '\u0002':
                return 0x02;
            case '\u0003':
                return 0x03;

        }
        return 0x00;
    }
}
