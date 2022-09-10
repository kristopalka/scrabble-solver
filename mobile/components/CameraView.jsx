import {Camera, CameraType} from 'expo-camera';
import {useState} from 'react';
import {StyleSheet, TouchableOpacity, useWindowDimensions, View} from 'react-native';
import Lens from "./camera/Lenx";
import {imageToText} from "../javascript/api"
import {logger} from "../javascript/logger";

export default function CameraView() {
    const {width, height} = useWindowDimensions();
    const [camera, setCamera] = useState(null);
    const [permission, requestPermission] = Camera.useCameraPermissions();

    if (!permission) return <View/>;
    if (!permission.granted) requestPermission();

    async function takeAPicture() {
        logger("Taking photo")
        const data = await camera.takePictureAsync({base64: true, quality: 0.7});
        await imageToText(data.base64)
    }


    return (
        <View style={styles.container}>
            <Camera style={styles.camera(width)}
                    ratio="4:3"
                    type={CameraType.back}
                    ref={r => setCamera(r)}>
                <View style={styles.captureBox}>
                    <Lens size={3 * width / 4}/>
                </View>
            </Camera>
            <TouchableOpacity style={styles.captureButton} onPress={takeAPicture}></TouchableOpacity>
        </View>
    );
}

const styles = StyleSheet.create({
    container: {
        height: "100%",
        width: "100%",
        justifyContent: 'space-evenly',
        alignItems: "center",
    },
    camera: (width) => ({
        width: width,
        height: Math.round((width * 4) / 3),
    }),
    captureBox: {
        flex: 1,
        backgroundColor: 'transparent',
        justifyContent: 'center',
        alignItems: "center",
        borderWidth: 40,
        borderColor:"black",
        borderRadius: 70,
        margin: -37,
    },
    captureButton: {
        width: 80,
        height: 80,
        backgroundColor: "white",
        borderRadius: 50,
        borderStyle: "solid",
        borderColor: "gray",
        borderWidth: 6,
    },
});
