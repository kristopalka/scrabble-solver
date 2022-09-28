import React from "react";
import {View} from "react-native";
import {marginBetween} from "../../javascript/css";

export default function Break(props) {
    const multiplier = props.multiplier ? props.multiplier : 1;

    return (
        <View style={{marginTop: marginBetween * multiplier}}></View>
    )
}