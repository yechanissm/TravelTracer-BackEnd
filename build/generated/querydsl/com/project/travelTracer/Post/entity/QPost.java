package com.project.travelTracer.Post.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QPost is a Querydsl query type for Post
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPost extends EntityPathBase<Post> {

    private static final long serialVersionUID = -714570622L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QPost post = new QPost("post");

    public final com.project.travelTracer.global.time.QBaseTimeEntity _super = new com.project.travelTracer.global.time.QBaseTimeEntity(this);

    public final StringPath address = createString("address");

    public final EnumPath<Category> category = createEnum("category", Category.class);

    public final com.project.travelTracer.location.entity.QCheckPoint checkpoint;

    public final ListPath<com.project.travelTracer.comment.entity.Comment, com.project.travelTracer.comment.entity.QComment> commentList = this.<com.project.travelTracer.comment.entity.Comment, com.project.travelTracer.comment.entity.QComment>createList("commentList", com.project.travelTracer.comment.entity.Comment.class, com.project.travelTracer.comment.entity.QComment.class, PathInits.DIRECT2);

    public final StringPath content = createString("content");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final ListPath<com.project.travelTracer.Image.Entity.Image, com.project.travelTracer.Image.Entity.QImage> image = this.<com.project.travelTracer.Image.Entity.Image, com.project.travelTracer.Image.Entity.QImage>createList("image", com.project.travelTracer.Image.Entity.Image.class, com.project.travelTracer.Image.Entity.QImage.class, PathInits.DIRECT2);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> lastModifiedDate = _super.lastModifiedDate;

    public final StringPath title = createString("title");

    public final com.project.travelTracer.member.entity.QMember writer;

    public QPost(String variable) {
        this(Post.class, forVariable(variable), INITS);
    }

    public QPost(Path<? extends Post> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QPost(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QPost(PathMetadata metadata, PathInits inits) {
        this(Post.class, metadata, inits);
    }

    public QPost(Class<? extends Post> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.checkpoint = inits.isInitialized("checkpoint") ? new com.project.travelTracer.location.entity.QCheckPoint(forProperty("checkpoint"), inits.get("checkpoint")) : null;
        this.writer = inits.isInitialized("writer") ? new com.project.travelTracer.member.entity.QMember(forProperty("writer")) : null;
    }

}

