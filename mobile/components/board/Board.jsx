import {StyleSheet, View} from 'react-native';
import Field from "./Field";

export default function Board(props) {
    function updateLetter(x, y, newLetter) {
        const content = props.content;
        content[x][y] = newLetter;
        props.onUpdateContent(content);
    }

    return (
        <View style={styles.grid}>
            {props.content.map((row, x) => {
                return <View style={styles.column} key={`c-${x}`}>{
                    row.map((field, y) => {
                        return <View style={styles.element} key={`r-${y}`}>
                            <Field
                                letter={field}
                                x={x} y={y}
                                onUpdateLetter={(newLetter) => updateLetter(x, y, newLetter)}
                            />
                        </View>
                    })
                }</View>
            })}
        </View>
    );
}

const styles = StyleSheet.create({
    grid: {
        backgroundColor: "white",
        width: 500,
        height: 500,
        borderWidth: 1,
        borderColor: "black",
        borderRadius: 8,
        padding: 2,
    },
    column: {
        flex: 1,
        flexDirection: "row",
        justifyContent: "center",
    },
    element: {
        flex: 1,
        justifyContent: "center",
        alignItems: "center",
        margin: 1,
    },
});
