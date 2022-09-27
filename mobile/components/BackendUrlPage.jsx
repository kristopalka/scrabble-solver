import {StyleSheet, Text, TextInput, View} from 'react-native';
import CustomButton from "./other/CustomButton";
import {useState} from "react";
import {requestInfo} from "../javascript/api";

export default function BackendUrlPage(props) {
    const [url, changeUrl] = useState(props.url)

    async function applyUrl() {
        try {
            console.log("XDDD")
            const x = await requestInfo();
            props.applyUrl(url);
        } catch (e) {

        }
    }

    return (
        <View style={styles.view}>
            <Text style={styles.text}>
                {"Cannot connect to server"}
            </Text>
            <Text style={styles.text_bottom}>
                {"Validate url and check\ninternet connection:"}
            </Text>
            <TextInput
                multiline
                style={styles.text_input}
                value={url}
                onChangeText={changeUrl}
                placeholder=""
                keyboardType="default"
            />
            <CustomButton title={"APPLY"} onPress={applyUrl}/>
        </View>
    );
}

const styles = StyleSheet.create({
    container: {
        flexGrow: 1,
    },
    text: {
        fontSize: 21,
        padding: 6,
        textAlign: "center",
    },
    text_bottom: {
        textAlign: "center",
        padding: 5,
        fontSize: 16,
        color: "black",
    },
    view: {
        width: '100%',
        height: '100%',
        backgroundColor: "#ffffff",
        alignItems: 'center',
        justifyContent: 'center',
    },
    text_input: {
        borderWidth: 1,
        borderColor: "gray",
        height: 40,
        width: 220,
        fontSize: 16,
        textDecorationColor: "black",
        textAlign: "center",
        marginBottom: 30,
        marginTop: 10,
    },
});