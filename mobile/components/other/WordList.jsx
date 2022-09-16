import React, {useState} from "react";
import {FlatList, StyleSheet, Text, TouchableOpacity, View} from "react-native";

export default function WordList(props) {
    const [selectedIndex, changeSelectedIndex] = useState(null);
    const words = props.words;

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
        marginTop: 6,
        width: "100%",
        borderWidth: 1.37,
        borderColor: "black",
        borderRadius: 15,
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
        marginRight: 10,
        marginLeft: 10,
        borderRadius: 14,
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