import {StyleSheet, Text, View} from 'react-native';
import Button from "./other/Button";
import Break from "./other/Break";
import {additionalTextSize, mainTextSize} from "../javascript/css";

export default function ErrorPage(props) {
    return (
        <View style={styles.container}>
            <Text style={styles.text}>{props.text}</Text>
            <Break/>

            {props.additionalText ?
                <View>
                    <Text style={styles.additionalText}>{props.additionalText}</Text>
                    <Break multiplier={3}/>
                </View>
                :
                <Break/>
            }

            <Button style={styles.button} onPress={props.goCamera} title={"OK"}></Button>
        </View>
    );
}

const styles = StyleSheet.create({
    container: {
        height: "100%",
        width: "100%",
        alignItems: "center",
        justifyContent: "center",
        backgroundColor: "white",
    },
    text: {
        fontSize: mainTextSize,
        textAlign: "center",
    },
    additionalText: {
        textAlign: "center",
        marginHorizontal: 40,
        fontSize: additionalTextSize,
        color: "black",
    },
    button: {
        width: 100,
    }
});
