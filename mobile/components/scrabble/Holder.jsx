import {StyleSheet, View} from 'react-native';
import Field from "./Field";

export default function Holder(props) {
    const fieldSize = 44;

    function updateLetter(x, newLetter) {
        const content = props.content;
        content[x] = newLetter;
        props.updateContent(content);
    }

    return (
        <View style={styles.grid(fieldSize)}>
            {props.content.map((field, x) => {
                return <View style={styles.element} key={x}>
                    <Field
                        size={fieldSize}
                        input={field}
                        editMode={props.editMode}
                        updateLetter={(newLetter) => updateLetter(x, newLetter)}
                        lettersValues={props.lettersValues}
                    />
                </View>
            })}
        </View>
    );
}

const styles = StyleSheet.create({
    grid: (fieldSize) => ({
        backgroundColor: "white",
        width: (fieldSize + (2 * (fieldSize / 32))) * 7,

        flexDirection: "row",
        borderWidth: (fieldSize / 32),
        borderColor: "black",
        borderRadius: 8 * (fieldSize / 32),
        padding: 4,
        alignSelf: "center",
    }),
    element: {
        flex: 1,
        justifyContent: "center",
        alignItems: "center",
    },
});
