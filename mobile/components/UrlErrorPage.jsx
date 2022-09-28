import {StyleSheet, Text, TextInput, View} from 'react-native';
import CustomButton from "./other/CustomButton";
import {useState} from "react";
import {requestInfo} from "../javascript/api";
import Break from "./other/Break";
import {additionalFontSize, borderRadius, borderWidth, mainFontSize} from "../javascript/css";
import {loggerErr} from "../javascript/logger";

export default function UrlErrorPage(props) {
    const [url, changeUrl] = useState(props.url)
    const [error, setError] = useState(false);

    async function applyUrl() {
        try {
            const settings = await requestInfo(url);
            props.applyUrl(url, settings);
        } catch (e) {
            setError(true);
            loggerErr(e);
        }
    }

    return (
        <View style={styles.view}>
            <Text style={styles.text}>{"Cannot connect to server"}</Text>
            <Break/>

            <Text style={styles.text_bottom}>{"Check internet connection\nand validate url:"}</Text>
            <Break/>


            <TextInput
                multiline
                style={styles.text_input(error)}
                value={url}
                onChangeText={changeUrl}
                placeholder=""
                keyboardType="default"
            />
            <Break multiplier={4}/>

            <CustomButton title={"APPLY"} onPress={applyUrl}/>
        </View>
    );
}

const styles = StyleSheet.create({
    container: {
        flexGrow: 1,
    },
    text: {
        fontSize: mainFontSize,
        textAlign: "center",
    },
    text_bottom: {
        textAlign: "center",
        fontSize: additionalFontSize,
        color: "black",
    },
    view: {
        width: '100%',
        height: '100%',
        backgroundColor: "#ffffff",
        alignItems: 'center',
        justifyContent: 'center',
    },
    text_input: (error) => ({
        borderWidth: borderWidth,
        borderRadius: borderRadius,
        borderColor: error ? "red" : "black",
        height: 40,
        width: 250,
        fontSize: additionalFontSize,
        textDecorationColor: "black",
        textAlign: "center",
    }),
});