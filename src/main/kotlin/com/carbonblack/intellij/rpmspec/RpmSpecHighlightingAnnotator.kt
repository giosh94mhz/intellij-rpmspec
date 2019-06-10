package com.carbonblack.intellij.rpmspec

import com.carbonblack.intellij.rpmspec.psi.*
import com.intellij.lang.annotation.AnnotationHolder
import com.intellij.lang.annotation.Annotator
import com.intellij.lang.annotation.HighlightSeverity
import com.intellij.psi.PsiElement

class RpmSpecHighlightingAnnotator : Annotator {
    override fun annotate(element: PsiElement, holder: AnnotationHolder) {
        if (element is RpmSpecMacro) {
            if (element.reference?.resolve() != null) {
                holder.createAnnotation(HighlightSeverity.INFORMATION, element.textRange, null).textAttributes = RpmSpecSyntaxHighligher.MACRO_ITEM
            } else {
                holder.createAnnotation(HighlightSeverity.WARNING, element.textRange, null).textAttributes = RpmSpecSyntaxHighligher.VERSION
            }
        } else if (element is RpmSpecDescriptionSection) {
            holder.createAnnotation(HighlightSeverity.INFORMATION, element.genericSection.genericBody.textRange, null).textAttributes = RpmSpecSyntaxHighligher.TEXT
        } else if (element is RpmSpecChangelogSection) {
            holder.createAnnotation(HighlightSeverity.INFORMATION, element.genericSection.genericBody.textRange, null).textAttributes = RpmSpecSyntaxHighligher.TEXT
        } else if (element is RpmSpecTagValue) {
            holder.createAnnotation(HighlightSeverity.INFORMATION, element.textRange, null).textAttributes = RpmSpecSyntaxHighligher.VALUE
        }
    }
}
