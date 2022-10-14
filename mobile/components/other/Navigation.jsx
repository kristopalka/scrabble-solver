import React, {useState} from "react";
import {StyleSheet, TouchableOpacity, View} from "react-native";
import {AntDesign, Entypo} from '@expo/vector-icons';
import Alert from "./Alert";

export default function Navigation(props) {
    let size = 35;
    let color = "black";

    const [alert, setAlert] = useState(false);


    return (
        <View style={styles.container}>
            <TouchableOpacity
                onPress={props.onLeftClick ? props.onLeftClick : null}>
                <AntDesign name={props.leftIco ? props.leftIco : "caretleft"} size={size} color={color}/>
            </TouchableOpacity>
            <TouchableOpacity onPress={() => setAlert(true)}>
                <Entypo name="help" size={size} color={color}/>
            </TouchableOpacity>
            <TouchableOpacity
                onPress={props.onRightClick ? props.onRightClick : null}>
                <AntDesign name={props.rightIco ? props.rightIco : "caretright"} size={size} color={color}/>
            </TouchableOpacity>

            <Alert
                visible={alert}
                setVisible={setAlert}
                title={props.helpTitle}
                message={props.helpMessage}
            />

        </View>
    )
}

const styles = StyleSheet.create({
    container: {
        width: "100%",
        flexDirection: "row",
        justifyContent: "space-evenly",
        alignItems: "center",
        marginVertical: 20,
    },
});