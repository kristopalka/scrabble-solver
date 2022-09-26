import {StyleSheet, Text, View} from 'react-native';
import {BallIndicator} from 'react-native-indicators';
import CustomButton from "./other/CustomButton";

export default function ErrorPage(props) {
    return (
        <View style={styles.container}>
            <Text style={styles.text}>{props.cause}</Text>
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
        fontSize: 20,
        marginBottom: 50,
    },
    button: {
        width: 100,
    }
});
