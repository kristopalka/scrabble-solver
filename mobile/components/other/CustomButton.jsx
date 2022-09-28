import React from "react";
import {StyleSheet, Text, TouchableOpacity} from "react-native";

export default function CustomButton(props) {

    return (
        <TouchableOpacity style={[styles.button, props.style]} onPress={props.onPress}>
            <Text style={styles.text}>
                {props.title}
            </Text>
        </TouchableOpacity>
    )
}


const styles = StyleSheet.create({
    button: {
        borderRadius: 10,
        borderWidth: 1.5,
        borderColor: "black",
        backgroundColor: "white",
        alignItems: "center",
        justifyContent: "center",
    },
    text: {
        padding: 10,
        textAlign: "center",
        color: "black",
        fontWeight: "bold",
        fontSize: 16,
    },
});