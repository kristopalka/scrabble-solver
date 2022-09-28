import {StyleSheet, Text, View} from 'react-native';
import CustomButton from "./other/CustomButton";
import Break from "./other/Break";
import {additionalFontSize, mainFontSize} from "../javascript/css";

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

            <CustomButton style={styles.button} onPress={props.onClick} title={"OK"}></CustomButton>
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
        fontSize: mainFontSize,
        textAlign: "center",
    },
    additionalText: {
        textAlign: "center",
        marginHorizontal: 40,
        fontSize: additionalFontSize,
        color: "black",
    },
    button: {
        width: 100,
    }
});
