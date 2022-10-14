import {Button, Dimensions, Image, StyleSheet, View} from 'react-native';

import Pointer from "./other/Pointer";
import {useRef, useState} from "react";
import Draggable from "react-native-draggable";
import Navigation from "./other/Navigation";


export default function EditCornersPage(props) {
    const imageUrl = `data:image/jpg;base64,${props.photo}`;
    const corners = props.corners;
    const width = Dimensions.get('window').width;
    const height = width * 4 / 3;
    const pointerSize = 50;
    const pointerWidth = 1.5;


    const imageBox = useRef(null);
    const pointerA = useRef(null);
    const pointerB = useRef(null);
    const pointerC = useRef(null);
    const pointerD = useRef(null);

    async function next() {
        function measure(pointer) {
            return new Promise((resolve, reject) => {
                pointer.current.measureLayout(
                    imageBox.current,
                    (x, y) => {
                        resolve({x: x / width, y: y / height})
                    })
            })
        }
        let corners = await Promise.all([
            measure(pointerA), measure(pointerB), measure(pointerC), measure(pointerD)
        ])

        props.goEditBoard(corners);
    }


    function getDraggable(pointer, corner) {
        return <Draggable
            x={corner.x * width} y={corner.y * height}
            minX={0} minY={0}
            maxX={width + pointerSize - pointerWidth} maxY={height + pointerSize - pointerWidth}
            children={
            <View ref={pointer} collapsable={false}>
                <Pointer size={pointerSize} width={pointerWidth}></Pointer>
            </View>}
        />
    }

    return (
        <View style={styles.container}>
            <View style={styles.imageBox(width, height)} ref={imageBox} collapsable={false}>
                <Image style={styles.image} source={{uri: imageUrl}}/>
                {getDraggable(pointerA, corners[0])}
                {getDraggable(pointerB, corners[1])}
                {getDraggable(pointerC, corners[2])}
                {getDraggable(pointerD, corners[3])}
            </View>

            <Navigation
                onLeftClick={props.goCamera}
                onRightClick={next}
                helpTitle={"Edit board corners"}
                helpMessage={"Algorithm tried to find corners of the board. Correct it if necessary. \n\nOuter corner of triple word premium square should be marked."}
            />
        </View>
    );
}

const styles = StyleSheet.create({
    container: {
        height: "100%",
        width: "100%",

        justifyContent: "flex-start",
        backgroundColor: "white",
    },
    imageBox: (w, h) => ({
        width: w,
        height: h,
        backgroundColor: "black",
        marginBottom: 100,
        marginTop: 100,
    }),
    image: {
        width: "100%",
        height: "100%",
    },

});
