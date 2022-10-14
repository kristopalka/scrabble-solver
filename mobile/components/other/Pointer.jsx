import {StyleSheet, View} from 'react-native';
import {borderWidth} from "../../javascript/css";


export default function Pointer(props) {
    let size = props.size ? props.size : 50;
    let width = props.width ? props.width : borderWidth;

    return (
        <View style={styles.container(size, width)}></View>
    );
}

const styles = StyleSheet.create({
    container: (size, width) => ({
        height: size,
        width: size,
        flexDirection: "column",
        borderStyle: "solid",
        borderWidth: width,
        borderColor: "black",
        borderRadius: size/2,
        borderTopLeftRadius: 0,
        backgroundColor: 'rgba(255, 255, 255, 0.2)',
    }),
});
