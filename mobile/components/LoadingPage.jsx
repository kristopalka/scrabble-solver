import {StyleSheet, View} from 'react-native';
import {BallIndicator} from 'react-native-indicators';

export default function LoadingPage(props) {
    return (
        <View style={styles.container}>
            <BallIndicator size={70} count={7} color={"gray"}/>
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
});
