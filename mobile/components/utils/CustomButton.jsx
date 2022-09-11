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
        borderWidth: 1,
        borderColor: "gray",
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