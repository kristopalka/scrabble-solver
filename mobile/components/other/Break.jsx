import React from "react";
import {StyleSheet, Text, TouchableOpacity, View} from "react-native";
import {marginBetween} from "../../javascript/css";

export default function Break() {

    return (
        <View style={styles.break}></View>
    )
}


const styles = StyleSheet.create({
    break: {
        marginTop: marginBetween,
    },
});