import {Camera, CameraType} from 'expo-camera';
import {useState} from 'react';
import {StyleSheet, TouchableOpacity, useWindowDimensions, View} from 'react-native';
import Lens from "./camera/Lenx";
import {logger} from "../javascript/logger";
import SegmentedControl from "./other/SegmentedControll";
import {Feather, Entypo, AntDesign} from '@expo/vector-icons';
import Alert from "./other/Alert";

export default function CameraPage(props) {
    const {width, height} = useWindowDimensions();
    const [camera, setCamera] = useState(null);
    const [permission, requestPermission] = Camera.useCameraPermissions();
    const [langIndex, setLangIndex] = useState(props.langIndex);
    const [alert, setAlert] = useState(false);

    if (!permission) return <View/>;
    if (!permission.granted) requestPermission();

    async function takePicture() {
        logger("Taking picture");
        const data = await camera.takePictureAsync({skipProcessing: true, base64: true, quality: 0.5, width: 750, height: 1000, exif: false});
        props.goEditCorners(data.base64, langIndex);
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

            <SegmentedControl
                tabs={props.langs}
                currentIndex={langIndex}
                onChange={setLangIndex}
                width={150}
                paddingVertical={10}
            />

            <View style={styles.navigation}>
                <TouchableOpacity onPress={() => {setAlert(true)}}>
                    <Entypo name="help" size={40} color={"white"}/>
                </TouchableOpacity>

                <TouchableOpacity onPress={takePicture}>
                    <AntDesign name="camera" size={80} color={"white"}/>
                </TouchableOpacity>

                <TouchableOpacity onPress={props.goEditBoard}>
                    <Feather name="square" size={40} color={"white"} />
                </TouchableOpacity>

                <Alert
                    visible={alert}
                    setVisible={setAlert}
                    title={"Scrabble Solver"}
                    message={"This app will help you to find good words on your Scrabble game. Take a photo of your Scrabble board and follow next steps.\n\nScrabble Solver v0.9\nMade by Krzysztof Pałka"}
                />

            </View>
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
        borderColor: "black",
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
    navigation: {
        width: "100%",
        flexDirection: "row",
        justifyContent: "space-evenly",
        alignItems: "center",
        marginVertical: 20,
    },
});
