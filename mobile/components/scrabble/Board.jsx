import {StyleSheet, View} from 'react-native';
import ReactNativeZoomableView from '@openspacelabs/react-native-zoomable-view/src/ReactNativeZoomableView';
import Field from "./Field";



export default function Board(props) {
    const fieldSize = 30;

    function updateLetter(x, y, newLetter) {
        const content = props.content;
        content[x][y] = newLetter;
        props.updateContent(content);
    }


    return (
        <ReactNativeZoomableView contentWidth={550} contentHeight={550} maxZoom={4} minZoom={0.7} initialZoom={1}>
            <View style={styles.grid(fieldSize)}>
                {props.content.map((row, x) => {
                    return <View style={styles.column} key={`r-${x}`}>{
                        row.map((field, y) => {
                            return <View style={styles.element} key={`c-${y}`}>
                                <Field
                                    size={fieldSize}
                                    input={field}
                                    editMode={props.editMode}
                                    updateLetter={(newLetter) => updateLetter(x, y, newLetter)}
                                    lettersValues={props.lettersValues}
                                />
                            </View>
                        })
                    }</View>
                })}
            </View>
        </ReactNativeZoomableView>
    );
}

const styles = StyleSheet.create({
    grid: (fieldSize) => ({
        backgroundColor: "white",
        width: (fieldSize + 2) * 15,
        height: (fieldSize + 2) * 15,
        borderWidth: 2,
        borderColor: "black",
        borderRadius: 8,
        padding: 2,
    }),
    column: {
        flex: 1,
        flexDirection: "row",
        justifyContent: "center",
    },
    element: {
        flex: 1,
        justifyContent: "center",
        alignItems: "center",
    },
});
