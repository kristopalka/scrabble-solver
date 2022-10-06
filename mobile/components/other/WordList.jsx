import React, {useState} from "react";
import {FlatList, StyleSheet, Text, TouchableOpacity, View} from "react-native";
import {borderRadius, borderWidth} from "../../javascript/css";

export default function WordList(props) {
    const [selectedIndex, changeSelectedIndex] = useState(null);
    const [words, setWords] = useState(props.words);

    function selectWord(object) {
        changeSelectedIndex(object.index);
        props.choseWord(object.item);
    }

    function renderItem(object) {
        const selected = (object.index === selectedIndex);
        return <TouchableOpacity style={styles.item(selected)} onPress={() => selectWord(object)}>
            <Text style={styles.itemValue(selected)}>{object.item.value.toUpperCase()}</Text>
            <Text style={styles.itemScore(selected)}>{object.item.score}</Text>
        </TouchableOpacity>
    }

    return (
        <View style={[styles.container, props.style]}>
            <FlatList style={styles.list} data={words} renderItem={renderItem} showsVerticalScrollIndicator={false}/>
        </View>
    )
}


const styles = StyleSheet.create({
    container: {
        width: "100%",
        borderWidth: borderWidth,
        borderColor: "black",
        borderRadius: borderRadius,
        backgroundColor: "white",
        alignItems: "center",
        justifyContent: "center",
    },
    list: {
        width: "100%",
        margin: 10,
    },
    item: (selected) => ({
        flexDirection: "row",
        justifyContent: "center",
        backgroundColor: selected ? "lightgray" : "transparent",
        marginHorizontal: 10,
        marginLeft: 10,
        borderRadius: borderRadius,
        alignItems: "center",
    }),
    itemValue: (selected) => ({
        padding: 8,
        textAlign: "center",
        color: "black",
        fontWeight: "bold",
        fontSize: 20,
    }),
    itemScore: (selected) => ({
        left: -4,
        top: 7,
        color: "black",
        fontWeight: "bold",
        fontSize: 12,
    }),
});